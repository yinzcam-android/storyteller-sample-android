package com.example.storytellerSampleAndroid.multiple.adapter

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.example.storytellerSampleAndroid.databinding.ListClipGridBinding
import com.example.storytellerSampleAndroid.databinding.ListClipRowBinding
import com.example.storytellerSampleAndroid.databinding.ListStoryGridBinding
import com.example.storytellerSampleAndroid.databinding.ListStoryRowBinding
import com.example.storytellerSampleAndroid.multiple.StorytellerViewDelegate
import com.example.storytellerSampleAndroid.theme.StorytellerThemes
import com.storyteller.ui.list.StorytellerClipsView
import com.storyteller.ui.list.StorytellerStoriesView
import kotlin.math.roundToInt

fun Int.dpToPx(context: Context): Int {
  return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics).roundToInt()
}

abstract class DemoElementViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

  open fun bind(uiElement: UiElement) {
    val startPadding = uiElement.padding.startPadding.dpToPx(view.context)
    val endPadding = uiElement.padding.endPadding.dpToPx(view.context)
    val topPadding = uiElement.padding.topPadding.dpToPx(view.context)
    val bottomPadding = uiElement.padding.bottomPadding.dpToPx(view.context)
    view.setPadding(startPadding, topPadding, endPadding, bottomPadding)
  }
}

class StoryRowViewHolder(private val binding: ListStoryRowBinding) : DemoElementViewHolder(binding.root) {
  companion object {
    fun inflate(parent: ViewGroup): StoryRowViewHolder {
      val binding = ListStoryRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return StoryRowViewHolder(binding)
    }
  }

  override fun bind(uiElement: UiElement) {
    super.bind(uiElement)
    val storyRow = uiElement as UiElement.StoryRow
    binding.storytellerRow.run {

      val heightResolved = storyRow.height.dpToPx(context)
      updateLayoutParams<ViewGroup.LayoutParams> {
        height = heightResolved
      }
      this.delegate = StorytellerViewDelegate(storyRow.id, storyRow.onFailure)

      configuration = StorytellerStoriesView.ListConfiguration(
        categories = storyRow.categories,
        cellType = storyRow.cellType,
        // set different theme than the Global one in SampleApp
        theme = StorytellerThemes.getCustomTheme(context)
      )
      if (storyRow.forceDataReload) {
        reloadData()
        storyRow.forceDataReload = false
      }
    }
  }
}

class StoryGridViewHolder(private val binding: ListStoryGridBinding) : DemoElementViewHolder(binding.root) {
  companion object {
    fun inflate(parent: ViewGroup): StoryGridViewHolder {
      val binding = ListStoryGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return StoryGridViewHolder(binding)
    }
  }

  override fun bind(uiElement: UiElement) {
    super.bind(uiElement)
    val storyGrid = uiElement as UiElement.StoryGrid
    binding.storytellerGrid.run {
      configuration = StorytellerStoriesView.ListConfiguration(
        categories = storyGrid.categories,
        cellType = storyGrid.cellType,
        // set different theme than the Global one in SampleApp
        theme = StorytellerThemes.getCustomTheme(context)
      )

      if (storyGrid.forceDataReload) {
        reloadData()
        storyGrid.forceDataReload = false
      }
    }
  }
}

class ClipGridViewHolder(private val binding: ListClipGridBinding) : DemoElementViewHolder(binding.root) {
  companion object {
    fun inflate(parent: ViewGroup): ClipGridViewHolder {
      val binding = ListClipGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return ClipGridViewHolder(binding)
    }
  }

  override fun bind(uiElement: UiElement) {
    super.bind(uiElement)
    val clipsGrid = uiElement as UiElement.ClipGrid
    binding.storytellerClipGrid.run {
      configuration = StorytellerClipsView.ListConfiguration(
        collection = clipsGrid.collection,
        cellType = clipsGrid.cellType,
        // set different theme than the Global one in SampleApp
        theme = StorytellerThemes.getCustomTheme(context)
      )
      this.delegate = StorytellerViewDelegate(clipsGrid.id, clipsGrid.onFailure)
      if (clipsGrid.forceDataReload) {
        reloadData()
        clipsGrid.forceDataReload = false
      }
    }
  }
}

class ClipRowViewHolder(private val binding: ListClipRowBinding) : DemoElementViewHolder(binding.root) {
  companion object {
    fun inflate(parent: ViewGroup): ClipRowViewHolder {
      val binding = ListClipRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return ClipRowViewHolder(binding)
    }
  }

  override fun bind(uiElement: UiElement) {
    super.bind(uiElement)
    val clipsRow = uiElement as UiElement.ClipRow
    binding.storytellerClipRow.run {
      val heightResolved = clipsRow.height.dpToPx(context)
      updateLayoutParams<ViewGroup.LayoutParams> {
        height = heightResolved
      }
      configuration = StorytellerClipsView.ListConfiguration(
        collection = clipsRow.collection,
        cellType = clipsRow.cellType,
        // set different theme than the Global one in SampleApp
        theme = StorytellerThemes.getCustomTheme(context)
      )
      this.delegate = StorytellerViewDelegate(clipsRow.id, clipsRow.onFailure)
      if (clipsRow.forceDataReload) {
        reloadData()
        clipsRow.forceDataReload = false
      }
    }
  }
}


