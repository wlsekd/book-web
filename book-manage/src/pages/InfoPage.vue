<template>
    <div>
        <!-- 头 -->
        <el-row :gutter="20" class="mgb20">
            <el-col :span="6">
                <el-card >
                    <div class="grid-content">
                        <div class="grid-content-center">
                            <div class="grid-num">{{userCount}}</div>
                            <div>用户总数</div>
                        </div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card >
                    <div class="grid-content">
                        <div class="grid-content-center">
                            <div class="grid-num">{{bookCount}}</div>
                            <div>书籍总数</div>
                        </div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card >
                    <div class="grid-content">
                        <div class="grid-content-center">
                            <div class="grid-num">{{authorCount}}</div>
                            <div>作者总数</div>
                        </div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card >
                    <div class="grid-content">
                        <div class="grid-content-center">
                            <div class="grid-num">{{bookListCount}}</div>
                            <div>书单总数</div>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
        <!-- 饼图 -->
        <el-row :gutter="20" class="mgb20">
            <el-col :span="12">
                <h3 class="mgb20">用户年龄分布</h3>
                <div style="background-color: white;">
                    <ve-line :data="userAge" ></ve-line>
                </div>
            </el-col>
            <el-col :span="12">
                <h3 class="mgb20">用户地域分布</h3>
                <div style="background-color: white;">
                    <ve-map  :data="userLocation" :visual-map="visualMap" ></ve-map>
                </div>
            </el-col>
        </el-row>
        <el-row :gutter="40" class="mgb20">
            <el-col :span="12">
                <h3 class="mgb20">书单类型</h3>
                <div style="background-color: white;">
                    <ve-pie :data="bookListStyle" ></ve-pie>
                </div>
            </el-col>
            <el-col :span="12">
                <h3 class="mgb20">作者地域分布</h3>
                <div style="background-color: white;">
                    <ve-map  :data="authorLocation" :visual-map="visualMap" ></ve-map>
                </div>
            </el-col>
        </el-row>
    </div>
    
</template>

<script>
import {getAllUser,getAllBook,getAllAuthor, getAllBookList} from '../api/index'
import { mapGetters } from 'vuex'
import {mixin} from '../mixins'
export default{
    mixins:[mixin],
    computed:{
        ...mapGetters([
            'loginIn'
        ])
    },
    data(){
        return{
            userCount:'0',
            bookCount:'0',
            authorCount:'0',
            bookListCount:'0',
            user:[],
            book:[],
            author:[],
            booklist:[],
            userAge:{       //按年龄分类的用户数
                columns:['年龄','总数'],
                rows:[
                    {'年龄':'0-12','总数':0},
                    {'年龄':'12-18','总数':0},
                    {'年龄':'18-25','总数':0},
                    {'年龄':'25-35','总数':0},
                    {'年龄':'35-45','总数':0},
                    {'年龄':'45-55','总数':0},
                    {'年龄':'55以上','总数':0},
                ]
            },
            options:{
                color:['#8741fa','#ffc0cb']
            },
            options1:{
                color:['yellow']
            },
            bookListStyle:{     //按书单的风格分类
                columns:['类型','总数'],
                rows:[
                    {'类型':'小说','总数':0},
                    {'类型':'文学','总数':0},
                    {'类型':'传记','总数':0},
                    {'类型':'政经','总数':0},
                    {'类型':'潮流','总数':0},
                    {'类型':'社科','总数':0},
                    {'类型':'科学','总数':0},
                    {'类型':'哲学','总数':0},
                ]
            },
            authorSex:{
                columns:['性别','总数'],
                rows:[
                    {'性别':'男','总数':0},
                    {'性别':'女','总数':0},
                    {'性别':'不明','总数':0},
                ]
            },
            visualMap:{
                type:'piecewise',
                pieces:[
                    {min:19,label:'1000以上'},
                    {min:13,max:18,label:'500-1000'},
                    {min:9,max:12,label:'100-500'},
                    {min:6,max:8,label:'50-100'},
                    {min:1,max:5,label:'50以下'},
                    {max:0,label:'无'}
                ]
            },
            authorLocation:{
                columns:['地区','总数'],
                rows:[   
                    {'地区': '北京','总数':0}, 
                    {'地区': '天津','总数':0},
                    {'地区': '河北','总数':0},
                    {'地区': '山西','总数':0},
                    {'地区': '内蒙古','总数':0},
                    {'地区': '辽宁','总数':0}, 
                    {'地区': '吉林','总数':0}, 
                    {'地区': '黑龙江','总数':0}, 
                    {'地区': '上海','总数':0}, 
                    {'地区': '江苏','总数':0}, 
                    {'地区': '浙江','总数':0}, 
                    {'地区': '安徽','总数':0}, 
                    {'地区': '福建','总数':0}, 
                    {'地区': '江西','总数':0}, 
                    {'地区': '山东','总数':0}, 
                    {'地区': '河南','总数':0}, 
                    {'地区': '湖北','总数':0}, 
                    {'地区': '湖南','总数':0}, 
                    {'地区': '广东','总数':0}, 
                    {'地区': '广西','总数':0}, 
                    {'地区': '海南','总数':0}, 
                    {'地区': '重庆','总数':0}, 
                    {'地区': '四川','总数':0}, 
                    {'地区': '贵州','总数':0}, 
                    {'地区': '云南','总数':0}, 
                    {'地区': '西藏','总数':0}, 
                    {'地区': '陕西','总数':0}, 
                    {'地区': '甘肃','总数':0}, 
                    {'地区': '青海','总数':0}, 
                    {'地区': '宁夏','总数':0}, 
                    {'地区': '新疆','总数':0}, 
                    {'地区': '香港','总数':0}, 
                    {'地区': '澳门','总数':0}, 
                    {'地区': '台湾','总数':0},
                    {'地区': '欧美','总数':0},
                    {'地区': '日韩','总数':0},
                    {'地区': '东南亚','总数':0},
                    {'地区': '其他','总数':0},
                ]
            },
            userLocation:{
                columns:['地区','总数'],
                rows:[   
                    {'地区': '北京','总数':0}, 
                    {'地区': '天津','总数':0},
                    {'地区': '河北','总数':0},
                    {'地区': '山西','总数':0},
                    {'地区': '内蒙古','总数':0},
                    {'地区': '辽宁','总数':0}, 
                    {'地区': '吉林','总数':0}, 
                    {'地区': '黑龙江','总数':0}, 
                    {'地区': '上海','总数':0}, 
                    {'地区': '江苏','总数':0}, 
                    {'地区': '浙江','总数':0}, 
                    {'地区': '安徽','总数':0}, 
                    {'地区': '福建','总数':0}, 
                    {'地区': '江西','总数':0}, 
                    {'地区': '山东','总数':0}, 
                    {'地区': '河南','总数':0}, 
                    {'地区': '湖北','总数':0}, 
                    {'地区': '湖南','总数':0}, 
                    {'地区': '广东','总数':0}, 
                    {'地区': '广西','总数':0}, 
                    {'地区': '海南','总数':0}, 
                    {'地区': '重庆','总数':0}, 
                    {'地区': '四川','总数':0}, 
                    {'地区': '贵州','总数':0}, 
                    {'地区': '云南','总数':0}, 
                    {'地区': '西藏','总数':0}, 
                    {'地区': '陕西','总数':0}, 
                    {'地区': '甘肃','总数':0}, 
                    {'地区': '青海','总数':0}, 
                    {'地区': '宁夏','总数':0}, 
                    {'地区': '新疆','总数':0}, 
                    {'地区': '香港','总数':0}, 
                    {'地区': '澳门','总数':0}, 
                    {'地区': '台湾','总数':0},
                    {'地区': '欧美','总数':0},
                    {'地区': '日韩','总数':0},
                    {'地区': '东南亚','总数':0},
                    {'地区': '其他','总数':0},
                ]
            }
        }
    },
    created(){
        this.isLogin()
    },
    mounted(){
        this.getUserCount(),
        this.getBookCount(),
        this.getAuthorCount(),
        this.getBookListCount()
    },
    methods:{
        //获取所有用户及其总数
        getUserCount(){
            this.user=[]
            getAllUser().then(res=>{
                this.user=res
                this.userCount=res.length
                this.getAge(this.user)
                for(let item of res){
                    this.getUserByLocation(item.location)
                }
            })
        },
        //获取用户年龄
        getAge(val){
            for(let item of val){
                let birthdays = new Date(item.birthday.replace(/-/g, "/"))
                let d = new Date()
                let age = d.getFullYear() - birthdays.getFullYear() - (d.getMonth() < birthdays.getMonth() || (d.getMonth() == birthdays.getMonth() && d.getDate() < birthdays.getDate()) ? 1 : 0);
                if(age<12){
                    this.userAge.rows[0].总数++
                }
                else if(age<18){
                    this.userAge.rows[1].总数++
                }
                else if(age<25){
                    this.userAge.rows[2].总数++
                }
                else if(age<35){
                    this.userAge.rows[3].总数++
                }
                else if(age<45){
                    this.userAge.rows[4].总数++
                }
                else if(age<55){
                    this.userAge.rows[5].总数++
                }
                else{
                    this.userAge.rows[6].总数++
                }
            }
        },
            
        setSex(sex,val){       //根据性别获取用户数
            let count=0
            for(let item of val){
                if(sex == item.sex){
                    count++
                }
            }
            return count
        },
        //获取书籍及总数
        getBookCount(){
            this.book=[]
            getAllBook().then(res=>{
                this.book=res
                this.bookCount=res.length
            })
        },
        //作者数量及数据
        getAuthorCount(){
            this.author=[]
            getAllAuthor().then(res=>{
                this.author=res
                this.authorCount=res.length
                this.authorSex.rows[1]['总数']=this.setSex(0,this.author)
                this.authorSex.rows[0]['总数']=this.setSex(1,this.author)
                this.authorSex.rows[2]['总数']=this.setSex(2,this.author)
                for(let item of res){
                    this.getAuthorByLocation(item.location)
                }
            })
        },
        //根据地区获取作者数量
        getAuthorByLocation(location){
            for(let item of this.authorLocation.rows){
                if(location.includes(item['地区'])){
                    item['总数']++
                }
            }
        },
        //根据地区获取用户数量
        getUserByLocation(location){
            for(let item of this.userLocation.rows){
                if(location.includes(item['地区'])){
                    item['总数']++
                }
            }
        },
        //书单数量及数据
        getBookListCount(){
            this.booklist=[]
            getAllBookList().then(res=>{
                this.booklist=res
                this.bookListCount=res.length
                for(let item of res){
                    this.getByStyle(item.style)
                }
            })
        },
        getByStyle(style){       //根据风格获取书籍
            for(let item of this.bookListStyle.rows){
                if(style.includes(item['类型'])){
                    item['总数']++
                }
            }
        },
    }

}
</script>

<style scoped>
.grid-content{
    display: flex;
    align-items: center;
    height: 50px;
}
.grid-content-center{
    flex: 1;
    text-align: center;
    font-size: 14px;
    color: darkgray;
}
.grid-num{
    font-size: 30px;
    font-weight: bold;
}
</style>