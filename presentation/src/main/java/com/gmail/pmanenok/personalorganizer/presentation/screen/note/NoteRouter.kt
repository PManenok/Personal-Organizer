package com.gmail.pmanenok.personalorganizer.presentation.screen.note

import com.gmail.pmanenok.personalorganizer.presentation.base.BaseRouter

class NoteRouter(activity: NoteActivity) : BaseRouter<NoteActivity>(activity) {

    /*fun goToStudentList() {
        Log.e("bbb", "router goToStudentList")
        replaceFragment(
            activity.supportFragmentManager,
            StudentListFragment.getInstance(),
            R.id.student_container,
            false
        )
    }

    fun goToStudentDetails(id: String) {
        Log.e("bbb", "router goToStudentDetails")
        val containerDetails = activity.findViewById<View>(R.id.student_container_details)
        if (containerDetails == null) {
            replaceFragment(
                activity.supportFragmentManager,
                StudentDetailsFragment.getInstance(id),
                R.id.student_container,
                true
            )
        } else {
            replaceFragment(
                activity.supportFragmentManager,
                StudentDetailsFragment.getInstance(id),
                R.id.student_container_details,
                false
            )
        }*/
}