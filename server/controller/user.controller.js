const UserModel = require('../model/user.model');
const ComicModel = require('../model/comic.model');
const notification = require("./notification.controller");
const bcrypt = require('bcrypt');


const register = async (req, res, next) => {
    try {
        let userCheck = await UserModel.findOne({ email: req.body.email });
        if (userCheck) {
            return res.status(203).json({
                data:null,
                msg: "Email đã tồn tại",
                success: false,
            })
        }

        let hash = await bcrypt.hash(req.body.passwrd, 10);
        let user = new UserModel({
            username: req.body.username,
            email: req.body.email,
            passwrd: hash,
            avatar: "https://steamuserimages-a.akamaihd.net/ugc/1648844222671420790/3B14F3B144A4934B6404582A276B2D8D29AA1275/?imw=512&&ima=fit&impolicy=Letterbox&imcolor=%23000000&letterbox=false",
            follow: [],
            history:[]
        })
        await user.save();
        console.log(user);
        let {_id, username, email, follow, avatar,history } = user;
        return res.status(200).json({
            data: {_id, username, email, follow, avatar,history },
            msg: "Thành công",
            success: true,
        })
    } catch (error) {
        console.log(error);
        return res.status(500).json({
            data:null,
            msg: "Thất bại",
            success: false,
        })
    }
}

const login = async (req, res, next) => {
    try {
        let userCheck = await UserModel.findOne({ email: req.body.email });
        if (userCheck) {
            let checkPasswrd = await bcrypt.compare(req.body.passwrd, userCheck.passwrd);
            if (checkPasswrd) {
                let { _id,username, email, follow, avatar,history } = userCheck;
                return res.status(200).json({
                    data: {_id, username, email, follow, avatar,history },
                    msg: "Thành công",
                    success: true,
                })
            }
            return res.status(203).json({
                data:null,
                msg: "Sai mật khẩu",
                success: false,
            })
        }

        return res.status(203).json({
            data:null,
            msg: "Email không tồn tại",
            success: false,
        })
    } catch (error) {
        console.log(error);
        return res.status(500).json({
            data:null,
            msg: "Thất bại",
            success: false,
        })
    }
}


const userFollowComics = async (req,res,next)=>{
    try {

        const obj = JSON.parse(req.body.listComics);
     
        let user = new UserModel({
            _id: req.params.id,
        });

        let userFL = await UserModel.findById({_id: req.params.id})
        let comicsFl = await ComicModel.findById({_id:obj.listComics[obj.listComics.length - 1]})

        await user.updateOne({ follow:obj.listComics});
        let msg = userFL.username + " " + "vừa theo dõi" + " " + comicsFl.name;

        if(!req.body.isFollow){
            notification.getNotification({titel:comicsFl.name,msg:msg,img:comicsFl.img});
        }
        
        return res.status(200).json({
            listComics: obj.listComics,
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

const getlistUser = async (req, res, next) => {
    try {
        let user_data = await UserModel.find();
        return res.status(200).json({
            data: user_data,
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


const deleteUser = async (req, res, next) => {
    try {
        let id = req.params.id;
        await UserModel.deleteOne({ _id: id });
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

const updateUser = async (req, res, next) => {
    try {

        let user = new UserModel({
            _id: req.params.id,
        })
        await user.updateOne({ username: req.body.username, email: req.body.email });

        return res.status(200).json({
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



module.exports = { login, register, getlistUser, deleteUser, updateUser,userFollowComics }