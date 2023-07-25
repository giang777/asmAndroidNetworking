const CommentModel = require("../model/comment.model");

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

module.exports = { getComment,postComment};