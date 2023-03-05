const modal = document.querySelector(".modal");
const img = document.querySelector(".img");
const modal_img = document.querySelector(".modal_content");
const span = document.querySelector(".close");

img.addEventListener('click', ()=>{
  modalDisplay("block");
  modal_img.src = img.src;
});
span.addEventListener('click', ()=>{
  modalDisplay("none");
});
modal.addEventListener('click', ()=>{
  modalDisplay("none");
});
function modalDisplay(text){
  modal.style.display = text;
}