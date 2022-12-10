import axiosClient from "./axiosClient";

const commentApi = {
    getAll: (articleId, params) => {
        const url = `/articles/${articleId}/comments`;

        return axiosClient.get(url, {params});
    },

    post: (articleId, data) => {
        const url = `/articles/${articleId}/comments`;

        return axiosClient.post(url, data, {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem("token")
            }
        });
    },

    hideShow: (articleId, commentId, data) => {
        const url = `/articles/${articleId}/comments/${commentId}/hide`;

        return axiosClient.put(url, data);
    }
}

export default commentApi;