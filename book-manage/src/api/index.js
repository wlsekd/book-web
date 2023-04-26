//业务类
import {get,post} from "./http"

//判断管理员是否登录成功
export const getLoginStatus = (params) => post(`admin/login/status`,params)

//****作者相关****
//添加作者
export const setAuthor = (params) => post ('author/add',params)
//查询所有作者
export const getAllAuthor = () => post ('author/allAuthor')
//更新作者信息
export const updateAuthor=(params)=>post('author/update',params)
//删除一个作者
export const delAuthor=(id)=>get(`author/delete?id=${id}`)
//查询一个作者
export const getAuthor=(id)=>get(`author/selectByPrimaryKey?id=${id}`)

//书本相关
//根据作者id查询
export const bookOfAuthorId=(id)=>get(`book/bookOfAuthorId?authorId=${id}`)
//查询所有书
export const getAllBook = () => post ('book/allBook')
//更新书籍信息
export const updateBook=(params)=>post(`book/update`,params)
//删除书
export const delBook=(id)=>get(`book/delete?id=${id}`)
//根据书id查询
export const bookOfBookId=(id)=>get(`book/detail?bookId=${id}`)
//根据书名获取
export const bookOfBookName=(bookName)=>get(`book/bookLikeName?name=${bookName}`)
//添加书籍
export const addBook=(params)=>post(`book/add`,params)

//****书单相关****
//添加书单
export const setBookList = (params) => post ('bookList/add',params)
//查询书单
export const getAllBookList = () => post ('bookList/allBookList')
//编辑书单
export const updateBookList=(params)=>post('bookList/update',params)
//删除一个书单
export const delBookList=(id)=>get(`bookList/delete?id=${id}`)

//****书单管理****
//根据书单id查询书籍列表
export const listBookDetail = (bookListId) => get (`listBook/detail?bookListId=${bookListId}`)
//给书单增加书籍
export const listBookAdd=(params)=>post('listBook/add',params)
//删除书单中的一本书
export const delListBook=(bookId,bookListId)=>get(`listBook/delete?bookId=${bookId}&bookListId=${bookListId}`)

//****用户相关****
//添加用户
export const setUser = (params) => post ('user/add',params)
//查询用户
export const getAllUser = () => post ('user/allUser')
//编辑用户
export const updateUser=(params)=>post('user/update',params)
//删除一个用户
export const delUser=(id)=>get(`user/delete?id=${id}`)
//根据用户id查询用户详细信息
export const getUserOfId=(id)=>get(`/user/selectByPrimaryKey?id=${id}`)

//****收藏相关 */
//通过用户id查询收藏
export const collectOfUserId=(id)=>get(`/collect/collectOfUserId?userId=${id}`)
//删除收藏
export const delCollection=(params)=>post(`/collect/del`,params)

//****评论相关 */
//返回指定书单的评论
export const commentOfBookListId=(bookListId)=>get(`/comment/commentOfBookListId?bookListId=${bookListId}`)
//删除评论
export const delComment=(id)=>get(`/comment/delete?id=${id}`)
//返回指定书的评论
export const commentOfBookId=(bookId)=>get(`/comment/commentOfBookId?bookId=${bookId}`)