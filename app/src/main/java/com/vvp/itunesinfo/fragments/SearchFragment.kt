package com.vvp.itunesinfo.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vvp.itunesinfo.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        activity!!.toolbar.title = ""

        buttonStartSearch.setOnClickListener{ getDataForSearch() }
    }



    // передача текста из editText фрагменту списка альбомов для загрузки
    private fun getDataForSearch() {

        val textForSearch = editEnterSearchText.text.toString()

        if (textForSearch.isEmpty()) {
            showToast(R.string.empty_request)
        } else {
            val bundle = Bundle()
            bundle.putString("textForSearch", textForSearch)
            findNavController().navigate(R.id.action_to_albumListFragment, bundle)
        }
    }


    // показ ошибок
    private fun showToast(message: Int){
        Toast.makeText(activity, getText(message), Toast.LENGTH_SHORT).show()
    }
}
