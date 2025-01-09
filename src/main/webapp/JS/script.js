document.getElementById('searchHeader').addEventListener('input', (event)=>{
    if (event.target.value.length >= 3){
        document.getElementById('searchBtnHeader').disabled = false;
    }
});