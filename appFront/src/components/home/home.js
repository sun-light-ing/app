import './home.css'
import {useNavigate} from "react-router-dom";

function Home() {
    const navigate = useNavigate();

    return (
        <div className='container'>
            <h1 style={{textAlign: 'center'}}>我的项目</h1>
            <h1><a href='/ae'>文章编辑</a></h1>
            <h1><a href='http://154.8.141.131:8080'>我的音乐</a></h1>
            <h1><a href='/ae'>我的图片</a></h1>
            <h1><a href='/ae'>我的纪念</a></h1>
            <h1><a href='/ae'>八月流火</a></h1>
        </div>
    )
}

export default Home; 