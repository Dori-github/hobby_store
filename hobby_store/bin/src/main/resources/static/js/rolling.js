function rollingTop(list, listItem, marginTop, animateTime, intervalTime){
  var id = setInterval(function(){
    if(!$(list + ' ' + listItem).first().is(':animated')){ //가아니면
      $(list + ' ' + listItem).first()
       .animate({'margin-top':marginTop+'px'},animateTime,function(){
       $(this).detach().appendTo(list).removeAttr('style');
      })
    }
  },intervalTime)
  return id;
}

function rollingLeft(list, listItem, animateTime, intervalTime){
  var id = setInterval(function(){
    if(!$(list+ ' '+ listItem).first().is(':animated')){
      var width = $(list+' '+listItem).first().innerWidth();
      $(list+' '+listItem).first()
        .animate({'margin-left':'-'+width}, animateTime, function(){
        $(this).detach().appendTo(list).removeAttr('style');
      })
   }
  },intervalTime);
  return id;
}

    
function rollingRight(list, listItem, animateTime, intervalTime){
  var id = setInterval(function(){
  if(!$(list+ ' '+ listItem).first().is(':animated')){
      var width = $(list+' '+listItem).last().innerWidth();
      $(list+' '+listItem).last().detach().prependTo(list)
      .css({'margin-left':-width+'px'}).animate({'margin-left':'0px'},animateTime);
    } 
  },intervalTime);
  return id;
}

function rolling(deirection, list, listItem, margin, animateTime,intervalTime){
  if(deirection.toLowerCase() == 'left'){
    return rollingLeft(list, listItem, animateTime, intervalTime);
  }
  else if(deirection.toLowerCase()=='right'){
    return rollingRight(list, listItem, animateTime, intervalTime);
  }else{
    return rollingTop(list, listItem, margin, animateTime, intervalTime);
  }
}

function reorganizing_rollingToRight(list, listItem, animateTime, intervalTime){
  var id=
  setInterval(function(){
    if(!$(list+ ' '+ listItem).first().is(':animated')){
      var width= $(list+ ' '+ listItem).first().width();
      $(list+ ' '+ listItem).first().detach().appendTo(list);
      $(list).css('margin-left',-width+'px').animate({'margin-left':'0px'},animateTime)
    }
  },intervalTime);
  return id;
}