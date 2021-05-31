package ru.geekbrains.android

class Presenter(val view: MainView, val model: CountersModel) {

    fun incrementCounter(id: Int) {
        id
            .let(model::incrementCounter)
            .let { counter -> view.showCounter(id, counter) }
    }
//        when(id){
//            R.id.btn_counter1 -> {
//                val nextValue = model.next(0)
//                view.setButtonText(0, nextValue.toString())
//            }
//            R.id.btn_counter2 -> {
//                val nextValue = model.next(1)
//                view.setButtonText(1, nextValue.toString())
//            }
//            R.id.btn_counter3 -> {
//                val nextValue = model.next(2)
//                view.setButtonText(2, nextValue.toString())
//            }
//        }
//    }
}