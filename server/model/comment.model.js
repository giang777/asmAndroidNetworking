const connect = require('./connect');
const UserModel = require('./user.model');
const ComicModel = require('./comic.model');
const Comment = new connect.mongoose.Schema(
    {
        id_comic:{
            type:connect.mongoose.Schema.Types.ObjectId,
            ref:ComicModel
        },
        id_user:
        {
            type:connect.mongoose.Schema.Types.ObjectId,
            ref:UserModel
        },
        content:
        {
            type:String,
            required:true,
        },
        time:{
            type:Date,
            require:true,
        }
        
    },
    {
        collection:"comments"
    }
)

const CommentModel = connect.mongoose.model("CommentModel",Comment);

module.exports = CommentModel;