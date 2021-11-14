
import 'package:flutter/material.dart';
import 'package:flutter_lrn/log.dart';
import 'package:flutter_lrn/template/template_widget.dart';

class TestOnPointWidget extends TemplateRoute {
  TestOnPointWidget(String name) : super(title: name);


  @override
  Widget getBody(BuildContext context) => PointerMoveIndicatorWidget();
}

class PointerMoveIndicatorWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _PointerMoveIndicatorState();
}

class _PointerMoveIndicatorState extends State<PointerMoveIndicatorWidget> {
  PointerEvent? _event;

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Listener(
          child: Container(
          alignment: Alignment.center,
            color: Colors.blue,
            width: 300.0,
            height: 150.0,
            child: Text(
              '${_event?.localPosition ?? ''}',
              style: TextStyle(color: Colors.white),
            ),
          ),
          onPointerDown: (PointerDownEvent event) => setState(() => _event = event),
          onPointerMove: (PointerMoveEvent event) => setState(() => _event = event),
          onPointerUp: (PointerUpEvent event) => setState(() => _event = event),
        ),

        Listener(
          child: IgnorePointer(
            child: Container(
              color: Colors.red,
              width: 200,
              height: 100,
            ),
          ),
          onPointerDown:(event){
            LogUtils.dd("${_event?.localPosition ?? ''}");
          } ,
        ),
      ],
    );
  }
}