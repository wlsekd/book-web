const book={
    state:{
        listOfBooks:[],      //当前查询的书的列表
        url:'',             //地址
        id:'',               //id
        tempList:{},        //某个书单或者作者的对象信息
        isCollect:false,    //是否收藏
    },
    getters:{
        listOfBooks:state=>{
            let listOfBooks=state.listOfBooks
            if(!listOfBooks.length){
                listOfBooks=JSON.parse(window.sessionStorage.getItem('listOfBooks')||null)
            }
            return listOfBooks
        },
        url:state=>{
            let url=state.url
            if(!url){
                url=JSON.parse(window.sessionStorage.getItem('url')||null)
            }
            return url
        },
        id:state=>{
            let id=state.id
            if(!id){
                id=JSON.parse(window.sessionStorage.getItem('id')||null)
            }
            return id
        },
        tempList:state=>{
            let tempList=state.tempList
            if(!tempList){
                tempList=JSON.parse(window.sessionStorage.getItem('tempList')||null)
            }
            return tempList
        },
        isCollect:state=>{
            let isCollect=state.isCollect
            if(!isCollect){
                isCollect=JSON.parse(window.sessionStorage.getItem('isCollect')||null)
            }
            return isCollect
        },

    },
    mutations:{
        setListOfBooks:(state,listOfBooks)=>{
            state.listOfBooks=listOfBooks
            window.sessionStorage.setItem('listOfBooks',JSON.stringify(listOfBooks))
        },
        setUrl:(state,url)=>{
            state.url=url
            window.sessionStorage.setItem('url',JSON.stringify(url))
        },
        setId:(state,id)=>{
            state.id=id
            window.sessionStorage.setItem('id',JSON.stringify(id))
        },
        setTempList:(state,tempList)=>{
            state.tempList=tempList
            window.sessionStorage.setItem('tempList',JSON.stringify(tempList))
        },
        setIsCollect:(state,isCollect)=>{
            state.isCollect=isCollect
            window.sessionStorage.setItem('isCollect',JSON.stringify(isCollect))
        },

    }
}

export default book