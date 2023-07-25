const connect = require('./connect');

const Chapter = new connect.mongoose.Schema(
    {
        
        comic_name :
        {
            type:String,
            required:true,
        },
        contents:
        {
            type:Array,
            required:true
        }
    },
    {
        collection:"chapters"
    }
)

const ChapterModel = connect.mongoose.model("ChapterModel",Chapter);

module.exports = ChapterModel;