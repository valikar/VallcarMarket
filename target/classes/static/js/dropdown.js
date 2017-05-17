var DATA = {
    ${manAndType}
};

function brandChanged() {
    var e = document.getElementById('brands');
    var brand = e.options[e.selectedIndex].value;
    var models = DATA[brand];
    var modelSelect = document.getElementById('models');
    modelSelect.style.display = 'block';
    modelSelect.innerHTML = '';

    for (var i = 0; i < models.length; i++) {
        var model = models[i];
        var opt = document.createElement('option');
        opt.value = model;
        opt.innerHTML = model;
        modelSelect.appendChild(opt);
    }
}
