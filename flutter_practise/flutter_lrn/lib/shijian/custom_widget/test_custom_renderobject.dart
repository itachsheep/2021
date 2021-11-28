
import 'package:flutter/material.dart';
import 'package:flutter_lrn/template/template_widget.dart';

import 'custom_check_box.dart';

class CustomRenderObject extends TemplateRoute {
  CustomRenderObject(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) {
    return CustomCheckboxTest();
  }
}

class CustomCheckboxTest extends StatefulWidget {
  const CustomCheckboxTest({Key? key}) : super(key: key);

  @override
  State<CustomCheckboxTest> createState() => _CustomCheckboxTestState();
}

class _CustomCheckboxTestState extends State<CustomCheckboxTest> {
  bool _checked = false;

  void _onChange(value) {
    setState(() => _checked = value);
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          CustomCheckbox(
            value: _checked,
            onChanged: _onChange,
          ),

          Padding(padding: EdgeInsets.only(top: 10)),

          SizedBox(
            width: 16,
            height: 16,
            child: CustomCheckbox(
              strokeWidth: 1,
              radius: 1,
              value: _checked,
              onChanged: _onChange,
            ),
          ),

          Padding(padding: EdgeInsets.only(top: 10)),

          SizedBox(
            width: 60,
            height: 60,
            child: CustomCheckbox(
              strokeWidth: 3,
              radius: 3,
              value: _checked,
              onChanged: _onChange,
            ),
          ),
        ],
      ),
    );
  }

}

