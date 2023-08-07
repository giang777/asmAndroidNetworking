const CommentModel = require("../model/comment.model");
const UserModel = require('../model/user.model');
const FeedbackModel = require('../model/feedback.model');
const BlogModel = require('../model/blog.model');
const ComicModel = require('../model/comic.model');
const notification = require("./notification.controller");

const getComment = async (req, res, next) => {
    try {
        if (req.query.limit) {
            let data_Comment = await CommentModel.find({ id_comic: req.query.id }).populate({ path: "id_user", select: "_id username avatar" }).limit(req.query.limit);
            return res.status(200).json({
                data: data_Comment,
                query: req.query.id,
                msg: "Thành công",
                success: true,
            })
        }
    
        let data_Comment = await CommentModel.find({ id_comic: req.query.id }).populate({ path: "id_user", select: "_id username avatar" });
        return res.status(200).json({
            data: data_Comment,
            query: req.query.id,
            msg: "Thành công",
            success: true,
        })
    } catch (error) {
        return res.status(500).json({
            msg: "Thất bại",
            success: false,
        })
    }
    
}

const postComment = async (req,res,next)=>{
    try {
        let comment = new CommentModel({
            id_comic:req.body.id_comic,
            id_user:req.body.id_user,
            content:req.body.content,
            time:new Date(),
        })

        await comment.save();
        let userCMT = await UserModel.findById({_id:req.body.id_user})
        let comicsCMT = await ComicModel.findById({_id:req.body.id_comic})
        let msg = `${userCMT.username} đã bình luận ${req.body.content}`;
        notification.getNotification({titel:comicsCMT.name,msg:msg,img:comicsCMT.img});

        return res.status(200).json({
            msg: "Thành công",
            success: true,
        })
    } catch (error) {
        return res.status(500).json({
            msg: "Thất bại",
            success: false,
        })
    }
}


const getFeedback = async (req, res, next) => {
    try {
        if (req.query.limit) {
            let data_Comment = await FeedbackModel.find({ id_blog: req.query.id }).populate({ path: "id_user", select: "_id username avatar" }).limit(req.query.limit);
            return res.status(200).json({
                data: data_Comment,
                query: req.query.id,
                msg: "Thành công",
                success: true,
            })
        }
    
        let data_Comment = await FeedbackModel.find({ id_blog: req.query.id }).populate({ path: "id_user", select: "_id username avatar" });
        return res.status(200).json({
            data: data_Comment,
            query: req.query.id,
            msg: "Thành công",
            success: true,
        })
    } catch (error) {
        return res.status(500).json({
            msg: "Thất bại",
            success: false,
        })
    }
    
}

const postFeedback = async (req,res,next)=>{
    try {
        let comment = new FeedbackModel({
            id_blog:req.body.id_blog,
            id_user:req.body.id_user,
            content:req.body.content,
            time:new Date(),
        })

        await comment.save();
       
        return res.status(200).json({
            msg: "Thành công",
            success: true,
        })
    } catch (error) {
        return res.status(500).json({
            msg: "Thất bại",
            success: false,
        })
    }
}

module.exports = { getComment,postComment,getFeedback,postFeedback};