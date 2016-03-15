# meituanDemo
这是个参考美团下拉菜单的demo，主要是用于学习PopupWindow的用法
View convertView = View.inflate(this,R.layout.pop,null);//获得要显示的下拉列表的布局
popupWindow = new PopupWindow(convertView
                              ,LinearLayout.LayoutParams.MATCH_PARENT
                              ,LinearLayout.LayoutParams.MATCH_PARENT);//根据该布局创建一个PopupWindow对象
popupWindow.setOutsideTouchable(true);//设置为外部可点击
popupWindow.setFocusable(true);//设置焦点
popupWindow.setBackgroundDrawable(new BitmapDrawable());
//PopupWindow这个view的点击事件，这里主要是判断当点击PopupWindow中ListView之外的地方时，就隐藏掉Listview
popupWindow.setTouchInterceptor(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getY() < popListView.getY() || event.getY() > popListView.getY() + popListView.getMeasuredHeight()) {
            if (popupWindow != null && popupWindow.isShowing()) {
                popupWindow.dismiss();
                return true;
            }
        }
        return false;
    }
});
最后通过popupWindow.showAsDropDown(view, 0, 2);设置，让PopupWindow显示在view的下方

popupwindow实现主要有一下几个步奏：
1、加载popupwindow所在布局
2、创建该布局中的需要操作的对象
3、通过popupwindow.show...()方法让popwindow显示在相应的位置
  当然，如果要做一些点击时间的处理，可以调用setTouchInterceptor（）来做点击事件的处理
popupwindow的好处，能够动态的将我们想要显示的view在对应的位置显示出来，而alertdialog则只能显示在屏幕中间
