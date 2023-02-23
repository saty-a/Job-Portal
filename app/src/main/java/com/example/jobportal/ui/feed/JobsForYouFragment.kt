package com.example.jobportal.ui.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobportal.adapter.ApplyJobAdapter
import com.example.jobportal.databinding.FragmentJobsForYouBinding
import com.example.jobportal.models.jobs

class JobsForYouFragment : Fragment() {

    private var _binding : FragmentJobsForYouBinding ? =null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentJobsForYouBinding.inflate(inflater,container,false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // this creates a vertical layout Manager
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<jobs>()

        // This loop will create 20 Views containing
        for (i in 1..20) {
            data.add(jobs("Mobile Dev", "lsdajfjalsdfj lflfasdjflakjfd jladf", "roorkee"))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = ApplyJobAdapter(data)

        // Setting the Adapter with the recyclerview
        binding.recyclerView.adapter = adapter

//        binding.floatingBtn.setOnClickListener {
//            data.add(jobs("Web Dev", "lsdajfjalsdfj lflfasdjflakjfd jladf", "delhi"))
//            adapter.notifyDataSetChanged()
//            println("fb clicked")
//        }

//        binding.bottomNavigation.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.appliedJob -> {
//                    Toast.makeText(context,"applied job",Toast.LENGTH_SHORT).show()
//                    findNavController().navigate(R.id.action_jobsForYouFragment_to_jobsAppliedByYouFragment)
//            }
//                R.id.home->{
//                    findNavController().navigate(R.id.action_jobsAppliedByYouFragment_to_jobsForYouFragment)
//                }
//            }
//            return@setOnItemSelectedListener true
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun loadFragment(fragment: Fragment){
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.container,fragment)
//        transaction.commit()
//    }

}