window.onload=function(){
	const youtube = 'https://youtu.be/';//유튜브
	let videoArray = [];
	let oembeds = document.getElementsByTagName('oembed');
	for(let i=0;i<oembeds.length;i++){
		let url = oembeds[i].getAttribute('url');
		if(url.includes(youtube)){
			var output = '';
			output += '<div style="position: relative; padding-bottom: 100%; height: 0; padding-bottom: 56.2493%;">';
			output += '<iframe src="https://www.youtube.com/embed/'+url.substring(youtube.length)+'"';
			output += 'style="position: absolute; width: 100%; height: 100%; top: 0; left: 0;"';
			output += 'frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>';
			output += '</iframe>';
			output += '</div>';
			videoArray.push(output);
		} 
	}  
	if(videoArray.length>0){
		let media = document.getElementsByClassName('media');
		for(let i=0;i<media.length;i++){
			media[i].innerHTML=videoArray[i];
		}
	}
};