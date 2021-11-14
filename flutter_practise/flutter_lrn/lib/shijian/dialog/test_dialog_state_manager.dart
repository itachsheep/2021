
// 单独封装一个内部管理选中状态的复选框组件
import 'package:flutter/material.dart';

class DialogCheckbox extends StatefulWidget {
  DialogCheckbox({
    Key? key,
    this.value,
    required this.onChanged,
  });

  final ValueChanged<bool?> onChanged;
  final bool? value;

  @override
  _DialogCheckboxState createState() => _DialogCheckboxState();
}

class _DialogCheckboxState extends State<DialogCheckbox> {
  bool? value;

  @override
  void initState() {
    value = widget.value;
    super.initState();
  }

  //然后，当我们运行上面的代码时我们会发现复选框根本选不中！为什么会这样呢？
  // 其实原因很简单，我们知道setState方法只会针对当前context的子树重新build，
  // 但是我们的对话框并不是在_DialogRouteState的build 方法中构建的，
  // 而是通过showDialog单独构建的，所以在_DialogRouteState的context中调用setState
  // 是无法影响通过showDialog构建的UI的。另外，我们可以从另外一个角度来理解这个现象，
  // 前面说过对话框也是通过路由的方式来实现的，
  // 那么上面的代码实际上就等同于企图在父路由中调用setState来让子路由更新，
  // 这显然是不行的！简尔言之，根本原因就是context不对。那如何让复选框可点击呢？
  // 通常有如下三种方法:
  //单独抽离出StatefulWidget
  //既然是context不对，那么直接的思路就是将复选框的选中逻辑单独封装成一个StatefulWidget，
  // 然后在其内部管理复选状态。
  // #
  @override
  Widget build(BuildContext context) {
    return Checkbox(
      value: value,
      onChanged: (v) {
        //将选中状态通过事件的形式抛出
       // widget.onChanged(v);
        setState(() {
          //更新自身选中状态
          value = v;
        });
      },
    );
  }


}
