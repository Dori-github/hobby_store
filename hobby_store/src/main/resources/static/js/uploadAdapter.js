class UploadAdapter {
    constructor(loader) {
        this.loader = loader;
    }

    upload() {
        return this.loader.file.then( file => new Promise(((resolve, reject) => {
            this._initRequest();
            this._initListeners( resolve, reject, file );
            this._sendRequest( file );
        })))
    }

    _initRequest() {
        const xhr = this.xhr = new XMLHttpRequest();
        //컨텍스 경로가 /springPage 형식일 때
        //xhr.open('POST', location.protocol + '//' + location.host + location.href.substring(location.href.indexOf(location.host)+location.host.length,location.href.indexOf('/',location.href.indexOf(location.host)+location.host.length+1)) + '/common/imageUploader.do', true);
        //컨텍스 경로가 / 형식일 때
        xhr.open('POST', location.protocol + '//' + location.host + '/common/imageUploader.do', true);
        xhr.responseType = 'json';
    }

    _initListeners(resolve, reject, file) {
        const xhr = this.xhr;
        const loader = this.loader;
        const genericErrorText = '파일을 업로드 할 수 없습니다.'

        xhr.addEventListener('error', () => {reject(genericErrorText)})
        xhr.addEventListener('abort', () => reject())
        xhr.addEventListener('load', () => {
            const response = xhr.response
            if(!response || response.error) {
                return reject( response && response.error ? response.error.message : genericErrorText );
            }

            resolve({
                default: response.url //업로드된 파일 주소
            })
        })
    }

    _sendRequest(file) {
        const data = new FormData()
        data.append('upload',file)
        this.xhr.send(data)
    }
}