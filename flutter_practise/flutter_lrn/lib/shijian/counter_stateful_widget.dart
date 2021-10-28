
import 'package:flutter/material.dart';
import 'package:flutter_lrn/log.dart';

const String tag = "CounterWidget";

class CounterWidget extends StatefulWidget {
  final int initValue;
  const CounterWidget({Key? key, this.initValue = 0}) : super(key: key);

  @override
  State<StatefulWidget> createState() {
      return _CounterWidgetState();
  }

}

class _CounterWidgetState extends State<CounterWidget> {
  int _counter = 0;

  @override
  void initState() {
    super.initState();
    _counter = widget.initValue;
    LogUtils.d(tag, "initState");
  }

  @override
  Widget build(BuildContext context) {
    LogUtils.d(tag, "build");
    return Scaffold(
      body: Center(
        child: TextButton(
          child: Text('$_counter'),
          onPressed: (){
            LogUtils.d(tag, "onPressed");
            setState(() {
              _counter++;
            });
          },
        ),
      ),
    );
  }

  @override
  void didUpdateWidget(CounterWidget oldWidget) {
    super.didUpdateWidget(oldWidget);
    LogUtils.d(tag,"didUpdateWidget ");
  }

  @override
  void deactivate() {
    super.deactivate();
    LogUtils.d(tag,"deactivate");
  }

  @override
  void dispose() {
    super.dispose();
    LogUtils.d(tag,"dispose");
  }

  @override
  void reassemble() {
    super.reassemble();
    LogUtils.d(tag,"reassemble");
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    LogUtils.d(tag,"didChangeDependencies");
  }

}