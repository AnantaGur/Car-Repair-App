import axios from 'axios';

export default {

    addRepairForm(form){
        return axios.post('/create_service_request', form);
    }

}