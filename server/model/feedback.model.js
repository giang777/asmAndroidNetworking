const connect = require('./connect');
const UserModel = require('./user.model');
const BlogModel = require('./blog.model');
const FeedBack = new connect.mongoose.Schema({
    content:{
        type:String,
        required:true,
    },
    id_user:{
        type:connect.mongoose.Schema.Types.ObjectId,
        ref:UserModel,
    },
    id_blog:{
        type:connect.mongoose.Schema.Types.ObjectId,
        ref:BlogModel,
    },
    time:{
        type:Date,
        require:true,
    }
},
{
    collection:'feedbacks',
}
)

const FeedBackModel = connect.mongoose.model("FeedBackModel",FeedBack);
module.exports = FeedBackModel;