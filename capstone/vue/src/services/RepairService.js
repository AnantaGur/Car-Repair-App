import axios from 'axios';

export default {

    addRepairForm(form){
        return axios.post('/create_service_request', form);
    },

    getRepair() {
        return axios.get('/my_service_requests');
    },

    getAllRepairs() {
        return axios.get('/all_service_requests');
    },

    getRepairEstimate(){
        return axios.get('/my_repair_estimates');
    },

    sendServiceEstimate(estimate) {
        return axios.post('/create_repair_estimate', estimate);
    },

    deleteRequest(requestId) {
        return axios.delete(`/delete_service_request/${requestId}`);
    },

    updateServiceStatus(status) {
        return axios.put('/update_request_status', status);
    },


}