const axios = require('axios');

async function getDetails(detailsId){
    const res = await axios.get(`http://localhost:8082/details/${detailsId}`
    );
    return res.data;
}

async function getDetailsByName(detailsName) {
    const r = await axios.get('http://localhost:8082/details', { params: { title: detailsName } });
    return r.data;
}

module.exports = {getDetails, getDetailsByName};