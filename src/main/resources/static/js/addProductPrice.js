
function expandTablePrice(tableName) {
  var newRow = document.createElement('tr');
  newRow.innerHTML =
    "<td> <input type='text' name='quantity'> </td>" +
    "<td> <input type='text' name='price'> </td>" +
    "<td><input type='button' value='Delete' class='btn btn-default' onClick='deleteTablePriceRow(this);'></td>";
  document.getElementById(tableName).appendChild(newRow);
  counter++;
}

function deleteTablePriceRow(o) {
  var p = o.parentNode.parentNode;
  p.parentNode.removeChild(p);
}

