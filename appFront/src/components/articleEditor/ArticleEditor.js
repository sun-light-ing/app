import React, {useState} from "react";
import {Box, TextField, Typography, Button, Paper} from "@mui/material";
import "react-quill/dist/quill.snow.css";
import ReactQuill from "react-quill";

function ArticleEditor() {
    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");

    const handlePublish = () => {
        alert('ok');
    };

    return (
        <Box sx={{padding: 4, maxWidth: "1200px", margin: "0 auto"}}>
            <Typography variant="h4" gutterBottom>
                在线文章编辑
            </Typography>

            <TextField
                label="文章标题"
                fullWidth
                variant="outlined"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                sx={{marginBottom: 3}}
            />

            <ReactQuill
                theme="snow"
                value={content}
                onChange={setContent}
                style={{
                    height: "300px",
                    marginBottom: "40px",
                    backgroundColor: "white",
                    borderRadius: "5px",
                }}
            />

            <Button
                variant="contained"
                color="primary"
                onClick={handlePublish}
                sx={{marginTop: 3}}
            >
                发表
            </Button>
        </Box>
    );
}

export default ArticleEditor;
