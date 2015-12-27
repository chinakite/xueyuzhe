window.showMaskLayer = function() {
    var ml = $('.mask-layer');
    if(ml.length == 0) {
        $('body').append('<div class="mask-layer"><div class="loader"><div class="loader-inner ball-clip-rotate"><div></div></div></div><div class="loading-text">正在加载数据，请稍候...</div></div>');
    }
    ml.show();
}

window.hideMaskLayer = function() {
    var ml = $('.mask-layer');
    ml.hide();
}