const express = require('express');
const router = express.Router();
const rp = require('request-promise');

/* GET home page. */
router.get('/health.json', (req, res) => {
    res.json({
        "status" : "UP"
    });
});

router.get('/github/:githubId', (req, res) => {
    const {githubId} = req.params;
    const options = {
        uri: 'https://api.github.com/users/'+githubId+'/repos',
        headers: {
            'User-Agent': '*'
        },
        json: true // Automatically parses the JSON string in the response
    };
    rp(options)
        .then(result => {
            res.json(result);
        }).catch(err => {
            console.log(err.message);
            res.json([]);
    });
});

module.exports = router;
