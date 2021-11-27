import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../../log.dart';

class TestListViewHeadWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => TestListViewHeadState();
}

String tag = "TestListViewHeadState";

class TestListViewHeadState extends State<TestListViewHeadWidget> {
  ScrollController scrollController = ScrollController();
  bool showToTopBtn = false;

  @override
  void initState() {
    super.initState();
    scrollController.addListener(() {
      LogUtils.d(tag, "offset -----> : ${scrollController.offset}");
      if (scrollController.offset < 1000 && showToTopBtn) {
        setState(() {
          showToTopBtn = false;
        });
      } else if (scrollController.offset >= 1000 && showToTopBtn == false) {
        setState(() {
          showToTopBtn = true;
        });
      }
    });
  }

  @override
  void dispose() {
    scrollController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("title-listview"),
      ),
      body: Scrollbar(
        child: Column(
          children: [
            ListTile(
              title: Text("listView - head"),
            ),
            Expanded(
                child: ListView.builder(
                    itemCount: 100,
                    itemExtent: 50,
                    controller: scrollController,
                    itemBuilder: (ctx, index) {
                      return ListTile(
                        title: Text("this is item: $index"),
                      );
                    })),
          ],
        ),
      ),
      floatingActionButton: showToTopBtn
          ? FloatingActionButton(
              onPressed: () {
                scrollController.animateTo(.0,
                    duration: Duration(milliseconds: 200), curve: Curves.ease);
              },
              child: Icon(Icons.arrow_upward),
            )
          : null,
    );
  }
}
