const connect = require('./connect');

const User = new connect.mongoose.Schema(
    {
        username:
        {
            type:String,
            required:true,
        },
        passwrd:
        {
            type:String,
            required:true,
        },
        email:
        {
            type:String,
            required:true,
        },
        avatar:{
            type:String,
            required:true,
            default:"https://ecdn.game4v.com/g4v-content/uploads/2020/12/Zhongli-lam-lai-01-game4v.jpg"
        },
        follow:{
            type:Array,
            default:[]
        },
        history:{
            type:Array,
            default:[]
        }
    },
    {
        collection:"users"
    }
)

const UserModel = connect.mongoose.model("UserModel",User);

module.exports = UserModel;