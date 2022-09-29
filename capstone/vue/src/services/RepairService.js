import axios from 'axios';

export default {

    addRepairForm(form){
        return axios.post('/create_service_request', form);
    },

    getRepair() {
        return axios.get('/my_service_requests');
    },

    sendServiceEstimate(estimate) {
        return axios.post('/create_repair_estimate', estimate);
    } 

}