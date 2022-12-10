import http from "./http-common";

class UploadFilesService {
    upload(file, onUploadProgress) {
        let formData = new FormData();

        formData.append("file", file);

        return http.post("/files/uploadFile", formData, {
            headers: {
                "Content-Type": "multipart/form-data"
            },
            onUploadProgress
        });
    }

    // getFiles() {
    //     return http.get("/downloadFile/bf32433c-bf67-41ca-9c99-d967a26fb5c6.png");
    // }
}

export default new UploadFilesService();