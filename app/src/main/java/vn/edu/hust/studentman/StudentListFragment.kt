package vn.edu.hust.studentman

import android.os.Bundle
import android.view.*
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class StudentListFragment : Fragment() {

    private lateinit var studentListView: ListView
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentRepository: StudentRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_list, container, false)
        studentListView = view.findViewById(R.id.list_view_students)
        studentRepository = StudentRepository(requireContext())

        lifecycleScope.launch {
            val students = studentRepository.getAllStudents()
            studentAdapter = StudentAdapter(requireContext(), students)
            studentListView.adapter = studentAdapter
        }

        registerForContextMenu(studentListView)
        setHasOptionsMenu(true)

        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                findNavController().navigate(R.id.action_studentListFragment_to_addEditStudentFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateStudentList() {
        lifecycleScope.launch {
            val students = studentRepository.getAllStudents()
            studentAdapter.updateData(students)
        }
    }
}
