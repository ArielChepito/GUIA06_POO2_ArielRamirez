$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unBind('check.bs.table').on('check.bs.table', function (e, row) {
            consAlum({})
            //setMessage('MESS_SUCC', 'Atención', 'Ha seleccionado a: ' + row.nomb.trim());
            console.log(JSON.stringify(row));
            console.log("Aquí es papá!:"+row.mail);
            $("[id='FormAlum:codi']").val(row.id);
//            $("[id='FormAlum:nomb']").val(row.nomb);
//            $("[id='FormAlum:nomb']").val(row.nomb);
//            $("[id='FormAlum:nomb']").val(row.nomb);
//            $("[id='FormAlum:nomb']").val(row.nomb);
//            $("[id='FormAlum:nomb']").val(row.nomb);
//            $("[id='FormAlum:nomb']").val(row.nomb);
        });
        return false;
    };
    
    INIT_OBJE_ALUM();
});

function INIT_OBJE_ALUM()
{
    $("#TablAlum").initBootTable();
}