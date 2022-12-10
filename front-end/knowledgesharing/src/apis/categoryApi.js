import axiosClient from "./axiosClient";

const categoryApi = {
    // retrieve all categories
    getAll: () => {
        const url = `/categories`;

        return axiosClient.get(url);
    },
    
    // retrieve category by id
    getById: (id) => {
        const url = `/categories/${id}`;

        return axiosClient.get(url);
    },

    // create new category
    post: (data) => {
        const url = '/categories';

        return axiosClient.post(url, data);
    }
}

export default categoryApi;