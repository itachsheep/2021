import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lrn/log.dart';

class TestGridViewWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => TestGridViewState();
}

class TestGridViewState extends State<TestGridViewWidget> {
  @override
  Widget build(BuildContext context) {
    // return ExtendGridView();
    // return SimpleGridView();
    return LoadMoreGridView();
  }
}

class LoadMoreGridView extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => LoadMoreGridViewState();
}
const tag = "LoadMoreGridView";
class LoadMoreGridViewState extends State {
  List<IconData> mdata = [];
  @override
  void initState() {
    super.initState();
    loadMore(true);
  }

  @override
  Widget build(BuildContext context) {
    return GridView.builder(
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 3,
          childAspectRatio: 1.0,
        ),
        itemBuilder: (ctx,index){
            if(index == mdata.length - 1 && mdata.length < 200) {
              loadMore(false);
            }
            return Icon(mdata[index]);
        }
        );
  }

  void loadMore(bool first) {
    int total = first ? 40 : 20;
    Future.delayed(Duration(milliseconds: 200)).then((value){
      //LogUtils.d(tag,"loadMore: $value}");
      setState(() {
        mdata.addAll([
          Icons.ac_unit,
          Icons.airport_shuttle,
          Icons.all_inclusive,
          Icons.beach_access,
          Icons.cake,
          Icons.free_breakfast,
        ]);
      });
    });
  }
}


class ExtendGridView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return GridView(
      gridDelegate: SliverGridDelegateWithMaxCrossAxisExtent(
          maxCrossAxisExtent: 80,
          childAspectRatio: 2,
        ),
      children:const [
        Icon(Icons.ac_unit),
        Icon(Icons.airport_shuttle),
        Icon(Icons.all_inclusive),
        Icon(Icons.beach_access),
        Icon(Icons.cake),
        Icon(Icons.free_breakfast),
      ],
    );
  }
}

class SimpleGridView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return GridView(
      gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 3,
          childAspectRatio: 2),
      children:const [
        Icon(Icons.ac_unit),
        Icon(Icons.airport_shuttle),
        Icon(Icons.all_inclusive),
        Icon(Icons.beach_access),
        Icon(Icons.cake),
        Icon(Icons.free_breakfast)
      ],
    );
  }
}
