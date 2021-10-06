function searchCase(){
	$('#searchInput').val('listCases_ByCompositeQuery');
	$('#fc-input').attr('placeholder', ' 找工作案件');
}

function searchPeople(){
	$('#searchInput').val('SEARCH_PEOPLE');
	$('#fc-input').attr('placeholder', ' 找專業人才');
}

function submitForm(e) {
    if (e.keyCode == 13) {
        $('#searchForm').submit();
    }
}