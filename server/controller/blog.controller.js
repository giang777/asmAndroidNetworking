const BlogModel = require("../model/blog.model");

const getListBlog =async (req,res,next)=>{
    try {
        let data = await BlogModel.find();
        return res.status(200).json({
            data: data,
            query: null,
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

module.exports = {getListBlog}