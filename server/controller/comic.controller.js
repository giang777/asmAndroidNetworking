const ComicModel = require('../model/comic.model');
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

        if(req.query.category){
            let data = await comicModel.find({id_category:req.query.category});
            return res.status(200).json({
                data: data,
                query: null,
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

const getLComicId = async (req, res, next) => {
    try {
        let comics = await ComicModel.findById({_id:req.query.id});
        return res.status(200).json({
            data: comics,
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
            _id: req.body.id,
        });


        switch (req.params.action) {
            case 'view':
                
            await comics.updateOne({
                view: comics.view + 1,
            });
            break;

            case 'follow':
            await comics.updateOne({
                follow: comics.follow + 1,
            });
            break;

            default:
            break;
        }

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

module.exports = { getListComic, getLComicId, updateComics }