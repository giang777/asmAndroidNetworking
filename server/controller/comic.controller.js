const comicModel = require('../model/comic.model');

const getListComic = async (req, res, next) => {
    try {
        let data = await comicModel.find();

        if (req.query.limit && req.query.page) {
            let skip = (req.query.page - 1) * req.query.limit;

            let total = await comicModel.countDocuments();
            let list = await comicModel.find().skip(skip).limit(req.query.limit);

            let totalPage = Math.ceil(total / req.query.limit);
            return res.status(200).json({
                data: list,
                totalPage: totalPage,
                totalData: total,
                query: {
                    limit: Number(req.query.limit),
                    page: Number(req.query.page),
                },
                msg: "Thành công",
                success: true,
            })
        }

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

const getListComicHot = (req, res, next) => {

}


const addComics = async (req, res, next) => {
    try {
        console.log(req.body);
        let comics = new comicModel({
            name: req.body.name,
            author: req.body.author,
            img: req.body.img,
            describe: req.body.describe,
            like: 0,
            view: 0,
            follow: 0,
            status: true,
            avatar_story: req.body.avatar_story

        })

        await comics.save();
        return res.status(201).json({
            msg: "Thành công",
            success: true,
        })
    } catch (error) {
        console.log(error);
        return res.status(500).json({
            msg: "Thất bại",
            success: false,
        })
    }
}

const deleteComics = async (req, res, next) => {
    try {
        await comicModel.deleteOne({ _id: req.params.id });
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

const updateComics = async (req, res, next) => {
    try {
        let comics = new comicModel({
            _id: req.params.id,
        });

        await comics.updateOne({
            name: req.body.name,
            author: req.body.author,
            image: req.body.image,
            describe: req.body.describe,
            avatar_story: req.body.avatar_story
        });
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

module.exports = { getListComic, getListComicHot, addComics, deleteComics,updateComics }