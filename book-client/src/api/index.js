//业务类
import {get,post} from "./http"
import Axios from "axios"

//****作者相关****
//查询所有作者
export const getAllAuthor = () => post ('author/allAuthor')
//根据地区查询作者
export const getAuthorOfLocation=(location) => get (`author/authorOfLocation?location=${location}`)
//根据作者姓名模糊查询
export const getAuthorLikeName = (name) => get (`author/authorOfName?name=${name}`)
//根据作者id获取作者
export const getAuthorByAuthorId=(id)=>get(`author/selectByPrimaryKey?id=${id}`)

//书相关
//根据作者id查询作品
export const bookOfAuthorId=(id)=>get(`book/bookOfAuthorId?authorId=${id}`)
//根据书id查询
export const bookOfBookId=(id)=>get(`book/detail?bookId=${id}`)
//根据书名模糊查询
export const bookLikeName=(keywords)=>get(`book/bookLikeName?name=${keywords}`)
//根据用户id查询作品
export const bookOfUserId=(id)=>get(`book/bookOfUserId?userId=${id}`)
//更新书籍信息
export const updateBook=(params)=>post(`book/update`,params)
//删除书
export const delBook=(id)=>get(`book/delete?id=${id}`)
//查询所有书
export const getAllBook = () => post ('book/allBook')

//****书单相关****
//查询所有书单
export const getAllBookList = () => post ('bookList/allBookList')
//返回标题包含文字的书单列表
export const getBookListLikeTitle=(keywords)=>get(`bookList/bookListLikeTitle?title=${keywords}`)
//根据风格查询书单
export const getBookListOfLikeStyle=(style)=>get(`bookList/likeStyle?style=${style}`)
//根据书单id查询
export const getBookListById = (id) => get (`bookList/selectByPrimaryKey?id=${id}`)
//添加书单
export const setBookList = (params) => post ('bookList/add',params)
//获取用户创建的书单
export const getBookListByUserId = (id) => get (`bookList/bookListOfUserId?userId=${id}`)
//编辑书单
export const updateBookList=(params)=>post('bookList/update',params)
//删除一个书单
export const delBookList=(id)=>get(`bookList/delete?id=${id}`)

//****书单中书本相关****
//根据书单id查询书籍列表
export const listBookDetail = (bookListId) => get (`listBook/detail?bookListId=${bookListId}`)
//删除书单中的一本书
export const delListBook=(bookId,bookListId)=>get(`listBook/delete?bookId=${bookId}&bookListId=${bookListId}`)
//给书单增加书籍
export const listBookAdd=(params)=>post('listBook/add',params)

//****用户相关****
//查询用户
export const getAllUser = () => post ('user/allUser')
//用户注册
export const signUp=(params)=>post(`user/add`,params)
//用户登录
export const loginIn=(params) => post(`/user/login`,params)
//根据用户id查询用户详细信息
export const getUserOfId=(id)=>get(`/user/selectByPrimaryKey?id=${id}`)
//更新用户信息
export const updateUserOfuserId=(params)=>post(`/user/update`,params)
//*****文件下载 */
//下载
export const download=(url)=>Axios({
    method:'get',
    url:url,
    responseType:'blob'
})

//******评分相关 */
//新增评分
export const rankAdd=(params)=>post(`/rank/add`,params)
//获取指定书单评分
export const avaScore=(bookListId)=>get(`/rank/avaScore?bookListId=${bookListId}`)
//获取指定书的评分
export const avaBookScore=(bookId)=>get(`/rank/avaBookScore?bookId=${bookId}`)

//******评论相关 */
//提交评论
export const addComment=(params)=>post(`/comment/add`,params)
//评论点赞
export const addLike=(params)=>post(`/comment/like`,params)
//返回当前书或书单的评论列表
export const getAllComment=(type,id)=>{
    if(type==0){//书
        return get(`/comment/commentOfBookId?bookId=${id}`)
    }else{//书单
        return get(`/comment/commentOfBookListId?bookListId=${id}`)
    }
}

//********收藏相关 */
//添加收藏
export const addCollect=(params)=>post(`/collect/add`,params)
//通过用户id查询收藏
export const collectOfUserId=(id)=>get(`/collect/collectOfUserId?userId=${id}`)
//是否收藏
export const existCollect=(params)=>post(`/collect/exist`,params)
//取消收藏
export const delCollect=(params)=>post(`/collect/delete`,params)