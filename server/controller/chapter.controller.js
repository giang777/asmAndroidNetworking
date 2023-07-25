const chapterModel = require('../model/chapter.model');

const getListChapter = async (req, res, next) => {
    try {
        let data = await chapterModel.findOne({ _id: req.query.id });

        return res.status(200).json({
            data: data,
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

module.exports = { getListChapter }