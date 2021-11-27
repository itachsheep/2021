import 'package:flutter/material.dart';

class TestListViewWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => TestListViewState();
}

class TestListViewState extends State<TestListViewWidget> {
  @override
  Widget build(BuildContext context) {
    String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    return Scaffold(
      appBar: AppBar(
        title: Text("singleScroll"),
      ),
      // body: ImutableListView() ,
      // body: SimpleListView() ,
      // body: SeparatedListView(),
      body: SimpleListView2(),
    );
  }
}

class SimpleListView2 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ListView.builder(
        prototypeItem: ListTile(title: Text("1")),
        itemBuilder: (ctx, index) {
          return ListTile(title: Text("item $index"));
        },
    );
  }
}

class SeparatedListView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    Widget diveder1 = Divider(color: Colors.red);
    Widget diveder2 = Divider(color: Colors.green);
    return ListView.separated(
      itemBuilder: (ctx, index) {
        return Text("this is item: $index");
      },
      separatorBuilder: (ctx, index) {
        return index % 2 == 0 ? diveder1 : diveder2;
      },
      itemCount: 50,
    );
  }
}

class SimpleListView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemBuilder: (context, index) {
        return ListTile(title: Text("this is item: $index"));
      },
      itemCount: 30,
      itemExtent: 40,
    );
  }
}

class ImutableListView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.lightGreen,
      child: ListView(
        shrinkWrap: true,
        padding: EdgeInsets.all(15.0),
        children: [
          const Text('I\'m dedicating every day to you'),
          const Text('Domestic life was never quite my style'),
          const Text('When you smile, you knock me out, I fall apart'),
          const Text('And I thought I was so smart'),
        ],
      ),
    );
  }
}
