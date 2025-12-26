const axios = require('axios');

async function getDetails(detailsId){
    const res = await axios.get(`http://localhost:8082/details/${detailsId}`
    );
    return res.data;
}

module.exports = {getDetails};