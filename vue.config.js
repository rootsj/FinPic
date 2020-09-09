// vue.config.js
module.exports = {
    pages:{
        loginpage:{
            entry: 'src/pages/loginpage/main.js',
            template: 'public/loginpage.html',
            filename: 'loginpage.out.html'
        },
        mainpage:{
            entry: 'src/pages/mainpage/main.js',
            template: 'public/mainpage.html',
            filename: 'mainpage.out.html'
        },
        signuppage:{
            entry: 'src/pages/signuppage/main.js',
            template: 'public/signuppage.html',
            filename: 'signuppage.out.html'
        },
        uploadpage:{
            entry: 'src/pages/uploadpage/main.js',
            template: 'public/uploadpage.html',
            filename: 'uploadpage.out.html'
        },
        mypage:{
            entry: 'src/pages/mypage/main.js',
            template: 'public/mypage.html',
            filename: 'mypage.out.html'
        },
        othersmypage:{
            entry: 'src/pages/othersmypage/main.js',
            template: 'public/othersmypage.html',
            filename: 'othersmypage.out.html'
        },
    },
};