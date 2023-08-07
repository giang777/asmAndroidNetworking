const connect = require('./connect');

const Blog = new connect.mongoose.Schema({
    img:{
        type:String,
        required:true,
    },
    titel:{
        type:String,
        required:true,
    },
    content:{
        type:String,
        required:true,
    },
},
{
    collection:'blogs',
}
)

const BlogModel = connect.mongoose.model("BlogModel",Blog);
module.exports = BlogModel;