Class {
	#name : #MMCDropListPresenter,
	#superclass : #MMCAbstractPresenter,
	#instVars : [
		'dropList',
		'label'
	],
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #specs }
MMCDropListPresenter class >> defaultSpec [
	^ SpecTableLayout row
		add: #label;
		add: #dropList;
		yourself
]

{ #category : #examples }
MMCDropListPresenter class >> runMe [
	<example>
	self new openWithSpec
]

{ #category : #accessing }
MMCDropListPresenter >> dropList [
	^ dropList
]

{ #category : #initialization }
MMCDropListPresenter >> initializeWidgets [
	dropList := self newDropList.
	dropList
		items: #(#name #LOC);
		displayBlock: [ :item | item asString ].
	label := self newLabel
]

{ #category : #accessing }
MMCDropListPresenter >> label [
	^ label
]

{ #category : #accessing }
MMCDropListPresenter >> label: aString [
	label label: aString
]

{ #category : #subscription }
MMCDropListPresenter >> update [
	dropList item: context queriesForActiveForm
]