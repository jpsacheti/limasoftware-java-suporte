$(".remove").click(function(event) {
	if (confirm("Tem certeza que deseja excluir?")) {
		var tag = $(this).closest(".ticket");
		$.ajax({
			url : $(this).attr("href"),
			type : 'DELETE'
		}).done(function(data, jqXHR, textStatus) {
			tag.fadeOut();
		}).fail(function(jqXHR, textStatus, err) {
			alert('Falha ao excluir! ' + err);
		});
	}
	event.preventDefault();
});