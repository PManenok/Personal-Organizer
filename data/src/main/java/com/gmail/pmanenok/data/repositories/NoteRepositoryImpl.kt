package com.gmail.pmanenok.data.repositories

import android.util.Log
import com.gmail.pmanenok.data.db.dao.NoteDao
import com.gmail.pmanenok.data.db.entity.TypedNoteDb
import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.domain.entity.NoteSearch
import com.gmail.pmanenok.domain.entity.NoteType
import com.gmail.pmanenok.domain.repositories.NoteRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject
import kotlin.collections.ArrayList

class NoteRepositoryImpl @Inject constructor(val noteDao: NoteDao) : NoteRepository {
    override fun getByDayRange(idBegin: Long, idEnd: Long): Flowable<List<Pair<Long, List<Note>>>> {
        Log.e("NoteRepositoryImpl","idBegin $idBegin, idEnd $idEnd")
        //return noteDao.getByDayRange(idBegin, idEnd)
        return Flowable.just(listOfAllNotes).map { list ->
            val map: MutableMap<Long, MutableList<Note>> = mutableMapOf()
            for (entry in list) {
                if (map.containsKey(entry.date)) map[entry.date]?.add(entry)
                else map[entry.date] = mutableListOf(entry)
            }
            var date = idBegin
            while (date <= idEnd) {
                if (!map.containsKey(date)) map[date] = mutableListOf()
                date += 86400000L
            }
            return@map map.toSortedMap().toList()
        }
    }

    override fun getAllTypedNoteByRange(dayFirst: Long, dayLast: Long): Flowable<Map<Long, List<Int>>> {
        return Flowable.just(listOfAllTypedNotes).map { list ->
            val map: MutableMap<Long, ArrayList<Int>> = mutableMapOf()
            for (entry in list) {
                if (map.containsKey(entry.day)) map[entry.day]?.add(NoteType.valueOf(entry.type).num)
                else map[entry.day] = arrayListOf(NoteType.valueOf(entry.type).num)
            }
            return@map map
        }
    }

    override fun getAllTypedNote(): Flowable<Map<Long, List<Int>>> {
        return Flowable.just(listOfAllTypedNotes).map { list ->
            val map: MutableMap<Long, ArrayList<Int>> = mutableMapOf()
            for (entry in list) {
                if (map.containsKey(entry.day)) map[entry.day]?.add(NoteType.valueOf(entry.type).num)
                else map[entry.day] = arrayListOf(NoteType.valueOf(entry.type).num)
            }
            return@map map
        }
    }

    companion object {
        const val TIME_BUFFER = 60000
    }

    val listOfAllNotes = listOf(
        Note("1544302800001", 1544475600000, NoteType.TYPE_NOTE, "My NOTE", "Hello from Notes"),
        Note("1544302800002", 1544475600000, NoteType.TYPE_BIRTHDAY, "My Birthday", ""),
        Note("1544302800003", 1544475600000, NoteType.TYPE_LIST, "My LIST", ""),
        Note("1544302800004", 1544389200000, NoteType.TYPE_BIRTHDAY, "My BIRTHDAY", "")
    )
    val listOfAllTypedNotes = listOf(
        TypedNoteDb(1544475600000, "TYPE_NOTE"),
        TypedNoteDb(1544475600000, "TYPE_BIRTHDAY"),
        TypedNoteDb(1544475600000, "TYPE_LIST"),
        TypedNoteDb(1544389200000, "TYPE_BIRTHDAY")
    )
    val listOfDayNotes = listOf(
        Note("1544302800001", 1544475600000, NoteType.TYPE_NOTE, "My NOTE", "Hello from Notes"),
        Note("1544302800002", 1544475600000, NoteType.TYPE_BIRTHDAY, "My Birthday", ""),
        Note("1544302800003", 1544475600000, NoteType.TYPE_LIST, "My LIST", "")
    )
    val note = Note("1544302800004", 1544389200000, NoteType.TYPE_BIRTHDAY, "My BIRTHDAY", "")
    private var lastTimeUpdate = 0L

    override fun getAll(): Flowable<List<Note>> {
        return Flowable.just(listOfAllNotes)
    }

    override fun getByDay(id: Long): Flowable<List<Note>> {
        return Flowable.just(listOfDayNotes).take(1)
    }

    override fun getById(id: String): Flowable<Note> {
        return Flowable.just(note)
    }

    override fun search(noteSearch: NoteSearch): Flowable<List<Note>> {
        return Flowable.just(listOfAllNotes)
    }

    override fun update(note: Note): Completable {
        return Completable.complete()
    }

    override fun add(note: Note): Completable {
        return Completable.complete()//.doOnComplete { noteDao.insert(note.transformToDb()) }
    }

    override fun delete(id: String): Completable {
        return Completable.complete()
    }
    /*//class StudentRepositoryImplTestDb(val apiService: RestService, val studentDao: StudentDao) : StudentRepository {
class StudentRepositoryImplTestDb @Inject constructor(val apiService: RestService, val studentDao: StudentDao) : StudentRepository {

    companion object {
        const val TIME_BUFFER = 60000
    }

    private var lastTimeUpdate = 0L

    override fun get(id: String): Observable<Student> {
        return studentDao.getById(id).take(1).toObservable().map { it.transformToDomain() }
    }

    override fun get(): Observable<List<Student>> {
        Log.e("aaa", "StudentRepositoryImplTestDb get")
        return studentDao.getAll().take(1).toObservable()
            .flatMap { studentDbList ->
                if (studentDbList.isEmpty() || System.currentTimeMillis() - lastTimeUpdate > TIME_BUFFER) {
                    Log.e(
                        "aaa",
                        "StudentRepositoryImplTestDb getAll db empty ${studentDbList.isEmpty()} || update ${System.currentTimeMillis() - lastTimeUpdate > TIME_BUFFER}"
                    )
                    apiService.getStudents()
                        .doOnNext {
                            lastTimeUpdate = System.currentTimeMillis()
                            studentDao.deleteAll()
                            studentDao.insert(it.map { it.transformToDb() })
                        }
                        .map { it.map { it.transformToDomain() } }
                        .onErrorReturn {
                            if (studentDbList.isEmpty()) {
                                throw it
                            } else {
                                Log.e("aaa", "StudentRepositoryImplTestDb connection error")
                                studentDbList.map { student -> student.transformToDomain() }
                            }
                        }
                } else {
                    Log.e("aaa", "StudentRepositoryImplTestDb getAll db not empty nor update")
                    Observable.just(studentDbList).map { list -> list.map { student -> student.transformToDomain() } }
                }
            }
    }


    override fun search(search: StudentSearch): Observable<List<Student>> {
        //TODO change search method!!!
        Log.e("aaa", "StudentRepositoryImplTestDb search")
        return get()
    }

    override fun update(student: Student): Completable {
        return Completable.fromObservable(apiService.updateStudent(student.transformToRequest()).doFinally {
            Log.e("aaa", "studentDao.update")
            studentDao.update(student.transformToDb())
        })
    }

    override fun save(student: Student): Completable {
        return Completable.fromObservable(apiService.saveStudent(student.transformToRequest()).doFinally {
            Log.e("aaa", "studentDao.insert")
            studentDao.insert(student.transformToDb())
        })
    }

    override fun delete(studentId: String): Completable {
        return Completable.fromObservable(apiService.deleteStudent(studentId).doFinally {
            Log.e("aaa", "studentDao.deleteById")
            studentDao.deleteById(studentId)
        })
    }
}*/
}