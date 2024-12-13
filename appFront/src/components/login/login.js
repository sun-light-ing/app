import React, {useState} from "react";
import {useNavigate} from "react-router-dom"; // 导入 useNavigate
import "./login.css";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    const navigate = useNavigate(); // 初始化 useNavigate

    const handleLogin = async (event) => {
        event.preventDefault();

        const loginData = {
            username,
            password,
        };

        try {
            const response = await fetch("http://154.8.141.131:9090/imgs/getImgs", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(loginData),
            });

            if (!response.ok) {
                throw new Error("登录失败，请检查用户名和密码");
            }

            const data = await response.json();
            console.log("登录成功：", data);

            // 存储 token
            localStorage.setItem("token", data.token);

            // 跳转到 /home
            navigate("/home");
        } catch (error) {
            setErrorMessage(error.message);
        }
    };

    return (
        <div className="login-container">
            <div className="login-form">
                <h1 className="login-title">欢迎回来</h1>
                {errorMessage && <p className="error-message">{errorMessage}</p>}
                <form onSubmit={handleLogin}>
                    <input
                        type="text"
                        placeholder="用户名"
                        className="login-input"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                    <input
                        type="password"
                        placeholder="密码"
                        className="login-input"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                    <button type="submit" className="login-button">登录</button>
                </form>
            </div>
        </div>
    );
}

export default Login;
