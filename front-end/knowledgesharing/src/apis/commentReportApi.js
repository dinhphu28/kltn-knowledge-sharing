import axiosClient from "./axiosClient";

const commentReportApi = {
    getAll: (params) => {
        const url = '/comment-reports';

        return axiosClient.get(url, {params});
    },

    getAllOfComment: (commentId) => {
        const url = `/comment-reports/${commentId}`;

        return axiosClient.get(url);
    },

    post: (data) => {
        const url = '/comment-reports';

        return axiosClient.post(url, data);
    },

    putSolvedUnsolved: (id, data) => {
        const url = `/comment-reports/${id}`;

        return axiosClient.put(url, data);
    }
}

export default commentReportApi;